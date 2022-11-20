package com.promineo.jeep.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.jeep.dao.JeepOrderDao;
import com.promineo.jeep.entity.Color;
import com.promineo.jeep.entity.Customer;
import com.promineo.jeep.entity.Engine;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.Option;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import com.promineo.jeep.entity.Tire;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepOrderService implements JeepOrderService {

  @Autowired
  private JeepOrderDao jeepOrderDao;

  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
//    log.info(orderRequest.toString() + " Called from the service layer");
    Customer customer = getCustomer(orderRequest);
    Jeep jeep = getModel(orderRequest);
    Color color = getColor(orderRequest);
    Engine engine = getEngine(orderRequest);
    Tire tire = getTire(orderRequest);
    List<Option> options = getOption(orderRequest);
    
    //@formatter:off
    BigDecimal price = jeep.getBasePrice()
        .add(color.getPrice())
        .add(engine.getPrice())
        .add(tire.getPrice());
    
    for(Option option : options) {
      price = price.add(option.getPrice());
    }
    //@formatter:on


    return jeepOrderDao.saveOrder(customer, jeep, color, engine, tire, price, options);
  }

  /**
   * @param orderRequest
   * @return
   */
  private List<Option> getOption(OrderRequest orderRequest) {
    return jeepOrderDao.fetchOptions(orderRequest.getOptions());
    
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Tire getTire(OrderRequest orderRequest) {
    return jeepOrderDao.fetchTire(orderRequest.getTire()).orElseThrow(() -> new NoSuchElementException(
        "Tire with ID = " + orderRequest.getTire() + " was not found."));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Engine getEngine(OrderRequest orderRequest) {
    return jeepOrderDao.fetchEngine("2_0_TURBO")
        .orElseThrow(() -> new NoSuchElementException(
            "Engine with ID = " + orderRequest.getEngine() + " was not found."));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Color getColor(OrderRequest orderRequest) {
    return jeepOrderDao.fetchColor(orderRequest.getColor())
        .orElseThrow(() -> new NoSuchElementException(
            "Color with ID = " + orderRequest.getColor() + " was not found."));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Jeep getModel(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors())
        .orElseThrow(() -> new NoSuchElementException(
            "Model with ID = " + orderRequest.getModel() + ", trim = " + orderRequest.getTrim()
                + "and " + orderRequest.getDoors() + " doors was not found."));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Customer getCustomer(OrderRequest orderRequest) {
    return jeepOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException(
            "Customer with ID = " + orderRequest.getCustomer() + " was not found."));
  }

}

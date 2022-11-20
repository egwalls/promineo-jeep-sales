package com.promineo.jeep.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.promineo.jeep.entity.Order;
import com.promineo.jeep.entity.OrderRequest;
import com.promineo.jeep.entity.Tire;
import com.promineo.jeep.entity.Color;
import com.promineo.jeep.entity.Customer;
import com.promineo.jeep.entity.Engine;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;
import com.promineo.jeep.entity.Option;

public interface JeepOrderDao {

  /**
   * @param customer
   * @return
   */
  Optional<Customer> fetchCustomer(String customer);

  /**
   * @param tireId
   * @return
   */
  Optional<Tire> fetchTire(String tireId);

  /**
   * @param model
   * @param trim
   * @param doors
   * @return
   */
  Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);

  /**
   * @param colorId
   * @return
   */
  Optional<Color> fetchColor(String colorId);

  /**
   * @param engineId
   * @return
   */
  Optional<Engine> fetchEngine(String engineId);

  /**
   * @param customer
   * @param jeep
   * @param color
   * @param engine
   * @param tire
   * @param price
   * @return
   */
  Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire,
      BigDecimal price, List<Option> options);

  /**
   * @param options
   * @return
   */
  List<Option> fetchOptions(List<String> optionIds);

  /**
   * @param orderRequest
   * @return
   */
//  Order createOrder(OrderRequest orderRequest);


}

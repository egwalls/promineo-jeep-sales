package com.promineo.jeep.service;

import java.util.List;
import com.promineo.jeep.entity.Jeep;

public interface JeepSalesService {

  /**
   * @param model
   * @param trim
   * @return
   */
  List<Jeep> fetchJeeps(String model, String trim);

}

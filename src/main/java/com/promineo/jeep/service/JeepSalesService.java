package com.promineo.jeep.service;

import java.util.List;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;

public interface JeepSalesService {

  /**
   * @param model
   * @param trim
   * @return
   */
  List<Jeep> fetchJeeps(JeepModel model, String trim);

}

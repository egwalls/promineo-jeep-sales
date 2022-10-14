/**
 * 
 */
package com.promineo.jeep.dao;

import java.util.List;
import com.promineo.jeep.entity.Jeep;
import com.promineo.jeep.entity.JeepModel;

/**
 * @author emily
 *
 */
public interface JeepSalesDao {

  /**
   * @param model
   * @param trim
   * @return
   */
  List<Jeep> fetchJeeps(JeepModel model, String trim);

}

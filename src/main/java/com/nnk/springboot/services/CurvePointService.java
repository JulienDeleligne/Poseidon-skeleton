package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * CurvePoint service.
 */
@Service
@Transactional
public class CurvePointService {

  @Autowired
  private CurvePointRepository curvePointRepository;

  /**
   * Find all CurvePoint.
   *
   * @return List of CurvePoint
   */
  public List<CurvePoint> findAll() {
    return curvePointRepository.findAll();
  }

  /**
   * Save the CurvePoint.
   * First check that the curvePoint is not null. If it's null, throw a IllegalArgumentException
   *
   * @param curvePoint CurvePoint to save
   */
  public void save(CurvePoint curvePoint) {
    Assert.notNull(curvePoint, "CurvePoint must not be null");
    curvePointRepository.save(curvePoint);
  }

  /**
   * Find the CurvePoint by id.
   * First check that the id is not null. If it's null, throw a IllegalArgumentException
   * Then search the CurvePoint. If it doesn't exist, throw a IllegalArgumentException
   *
   * @param id The CurvePoint id
   * @return CurvePoint found
   */
  public CurvePoint findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return curvePointRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
  }

  /**
   * Delete the CurvePoint by id
   *
   * @param id The CurvePoint id
   */
  public void delete(Integer id) {
    curvePointRepository.deleteById(findById(id).getId());
  }

  /**
   * check that the curvePointToSave is not null, then update the CurvePoint and save it.
   *
   * @param curvePointToSave The CurvePoint to save
   * @param id The CurvePoint id to update
   */
  public void update(CurvePoint curvePointToSave, Integer id) {
    Assert.notNull(curvePointToSave, "CurvePointToSave must not be null");
    CurvePoint curvePoint = findById(id);
    curvePoint.setCurveId(curvePointToSave.getCurveId());
    curvePoint.setTerm(curvePointToSave.getTerm());
    curvePoint.setValue(curvePointToSave.getValue());
    save(curvePoint);
  }
}

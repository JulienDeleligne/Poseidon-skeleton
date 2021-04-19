package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class CurvePointService {

  @Autowired
  private CurvePointRepository curvePointRepository;

  public List<CurvePoint> findAll() {
    return curvePointRepository.findAll();
  }

  public void save(CurvePoint curvePoint) {
    Assert.notNull(curvePoint, "CurvePoint must not be null");
    curvePointRepository.save(curvePoint);
  }

  public CurvePoint findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return curvePointRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
  }

  public void delete(Integer id) {
    curvePointRepository.deleteById(findById(id).getId());
  }

  public void update(CurvePoint curvePointToSave, Integer id) {
    Assert.notNull(curvePointToSave, "CurvePointToSave must not be null");
    CurvePoint curvePoint = findById(id);
    curvePoint.setCurveId(curvePointToSave.getCurveId());
    curvePoint.setTerm(curvePointToSave.getTerm());
    curvePoint.setValue(curvePointToSave.getValue());
    save(curvePoint);
  }
}

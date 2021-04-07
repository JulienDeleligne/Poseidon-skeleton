package com.nnk.springboot.domain;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

  @Autowired
  private CurvePointRepository curvePointRepository;

  @Test
  public void curvePointTest() {
    CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

    // Save
    curvePoint = curvePointRepository.save(curvePoint);
    assertThat(curvePoint.getId()).isNotNull();
    assertThat(curvePoint.getCurveId()).isEqualTo(10);

    // Update
    curvePoint.setCurveId(20);
    curvePoint = curvePointRepository.save(curvePoint);
    assertThat(curvePoint.getCurveId()).isEqualTo(20);

    // Find
    List<CurvePoint> listResult = curvePointRepository.findAll();
    Assertions.assertThat(listResult.size() > 0).isTrue();

    // Delete
    Integer id = curvePoint.getId();
    curvePointRepository.delete(curvePoint);
    Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
    Assertions.assertThat(curvePointList).isNotPresent();
  }
}

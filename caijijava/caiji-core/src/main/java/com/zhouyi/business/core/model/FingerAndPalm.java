package com.zhouyi.business.core.model;

import com.zhouyi.business.core.vo.xml.FingerXml;
import lombok.Data;

import java.util.List;

/**
 * 指掌纹模型
 */
@Data
public class FingerAndPalm {
    List<? extends LedenCollectFinger> fingers;
    List<LedenCollectPalm> palms;
    List<LedenCollectFourfinger> fourfingers;
    List<LedenCollectPhalange> phalanges;
    List<LedenCollectFullpalm> fullpalms;
}

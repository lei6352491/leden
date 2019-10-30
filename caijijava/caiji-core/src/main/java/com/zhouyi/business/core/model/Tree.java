package com.zhouyi.business.core.model;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private String label;

    private List<Tree> children = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}

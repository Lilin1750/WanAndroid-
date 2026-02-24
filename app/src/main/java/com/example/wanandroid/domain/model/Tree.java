//Tree的model
package com.example.wanandroid.domain.model;

import java.util.List;

public class Tree {
    private final int id;
    private final String name;
    private final List<Tree> children;

    public Tree(int id, String name, List<Tree> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tree> getChildren() {
        return children;
    }

    //业务方法,判断是否为Leaf节点.即Tree的最低一级结构
    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return id == tree.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

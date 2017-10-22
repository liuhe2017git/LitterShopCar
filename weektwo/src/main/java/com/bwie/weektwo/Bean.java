package com.bwie.weektwo;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/11 13 30
 */

public class Bean {
    private boolean ischecked;
    private String name;

    public Bean(boolean ischecked, String name) {
        this.ischecked = ischecked;
        this.name = name;
    }

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "ischecked=" + ischecked +
                ", name='" + name + '\'' +
                '}';
    }
}

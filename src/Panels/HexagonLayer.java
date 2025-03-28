package Panels;
import java.awt.*;
import javax.swing.*;

public class HexagonLayer implements LayoutManager {

    private int centerSize;
    private int surroundingSize;

    public HexagonLayer(int centerSize, int surroundingSize) {
        this.centerSize = centerSize;
        this.surroundingSize = surroundingSize;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {

    }
}


package Algorythms.Structures;

import java.util.HashSet;

/**
 *
 * @author Ilys Shkuratov
 */
public class ArrayTree {
    String[] values;
    int[][] indexes;
    int size;
    int spaces = 0;
    
    public ArrayTree() {
        size = 100;
        this.values = new String[size];
        indexes = new int[2][size];
    }
    
    public ArrayTree(int size) {
        this.size = size;
        values = new String[size];
        indexes = new int[2][size];
    }
    
    public ArrayTree(String[] values) {
        this.size = values.length;
        this.values = values;
        this.indexes = new int[2][size];
        createIndex();
    }

    
    public void sealing() {
        int p = 0;
        HashSet<Integer> hs = new HashSet();
        hs.add(0);
        for (int i = 0; i < values.length; i++) {
            if (!hs.contains(i)) {
                values[i] = "";
                indexes[0][i] = 0;
                indexes[1][i] = 0;
            }
            for (int j = 0; j < indexes.length; j++) {
                if (indexes[j][i] == 0)
                    spaces ++;
                else {
                    hs.add(indexes[j][i]);
                    indexes[j][i] -= spaces;
                }
            }
        }
        for (int i = 0; i < values.length; i++) {
            if (values[p].isEmpty() && (i + 1 < values.length) && !values[i + 1].isEmpty()) {
                values[p] = values[i + 1];
                values[i + 1] = "";
                indexes[0][p] = indexes[0][i + 1];
                indexes[1][p] = indexes[1][i + 1];
                indexes[0][i + 1] = 0;
                indexes[1][i + 1] = 0;
                p ++;
            }
            if (!values[p].isEmpty()) p ++;
        }
    }
    
    
    public int getSize() {
        return size;
    }
    
    public String getValue(int index) {
        if (index < size) {
            return values[index];
        } else
            return null;
    }
    
    public void setLeft(int index, String value) {
        int i = index * 2 + 1;
        if (i < size) {
            values[i] = value;
        }
    }
    
    public String getLeft(int index) {
        int i;
        if (index < size) {
            i = indexes[0][index];
        } else return null;
        if (i < size && i != 0)
            return values[i];
        else
            return null;
    }
    
    public void setRight(int index, String value) {
        int i = index * 2 + 2;
        if (i < size) {
            values[i] = value;
        }
    }
    
    public String getRight(int index) {
        int i;
        if (index < size) {
            i = indexes[1][index];
        } else return null;
        if (i < size && i != 0)
            return values[i];
        else
            return null;
    }
    
    public int left(int index) {
        if (index < size)
            return indexes[0][index];
        else return -1;
    }
    
    public int right(int index) {
        if (index < size)
            return indexes[1][index];
        else return -1;
    }

    @Override
    public String toString() {
        StringBuilder sbv = new StringBuilder();
        StringBuilder sbf = new StringBuilder();
        StringBuilder sbs = new StringBuilder();
        sbf.append(String.format("%10s", "Left: "));
        sbs.append(String.format("%10s", "Right: "));
        sbv.append("ArrayTree:\n" + "Size is: ").append(size).append('\n').append(String.format("%10s", "Values: "));
        for (int i = 0; i < values.length; i++) {
            sbv.append(String.format("%3s, ", values[i]));
            sbf.append(String.format("%3d, ", indexes[0][i]));
            sbs.append(String.format("%3d, ", indexes[1][i]));
//            sbf.append(indexes[0][i]).append(", ");
//            sbs.append(indexes[1][i]).append(", ");
        }
        sbv.append('\n').append(sbf).append('\n').append(sbs).append('\n');
        return sbv.toString();
    }
    
    
    
    private void createIndex() {
        int temp;
        for (int i = 0; i < values.length; i++) {
            if (!"".equals(values[i])) {
                temp = i * 2 + 1;
                if (temp < values.length && !values[temp].isEmpty()) 
                    indexes[0][i] = temp;
                temp = i * 2 + 2;
                if (temp < values.length && !values[temp].isEmpty())
                    indexes[1][i] = temp;
            }
        }
    }
    
}

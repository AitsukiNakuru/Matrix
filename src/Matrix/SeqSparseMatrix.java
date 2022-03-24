package Matrix;

import javafx.scene.control.Alert;

/**
 * 稀疏矩阵的压缩存储
 *
 * 稀疏矩阵三元组顺序表
 *
 * 三元组顺序存储的稀疏矩阵类
 *
 * @author clarck
 *
 */
public class SeqSparseMatrix {
    // 矩阵行数、列数
    private final int rows;
    private final int columns;

    // 稀疏矩阵三元组顺序表
    private final SeqList<Triple> list;


    public SeqSparseMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("矩阵行数或列数为非正数");

        this.rows = rows;
        this.columns = columns;
        // 构造空顺序表，执行SeqList()构造方法
        this.list = new SeqList<>();
    }

    /**
     * 返回矩阵第i行第j列元素，排序顺序表的顺序查找算法，O(n)
     *
     */
    public int get(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        }

        Triple item = new Triple(i, j, 0);
        int k = 0;
        Triple elem = this.list.get(k);
        // 在排序顺序表list中顺序查找item对象
        while (k < this.list.length() && item.compareTo(elem) >= 0) {
            // 只比较三元组元素位置，即elem.row == i && elem.column == j
            if (item.compareTo(elem) == 0) {
                return elem.value;
            }
            // 查找到(i, j), 返回矩阵元素
            k++;
            elem = this.list.get(k);
        }
        return 0;
    }

    /**
     * 以三元组设置矩阵元素
     *
     */
    public void set(Triple elem) {
        this.set(elem.row, elem.colum, elem.value);
    }

    /**
     * 设置矩阵第row行第column列的元素值为value，按行主序在排序顺序表list中更改或插入一个元素的三元组， O(n)
     *
     */
    public void set(int row, int column, int value) {

        if (value == 0)
            return;

        if (row >= this.rows || column >= this.columns) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("三元组的行或列序号越界");
            alert.showAndWait();
            throw new IllegalArgumentException("三元组的行或列序号越界");
        }

        Triple elem = new Triple(row, column, value);
        int i = 0;
        // 在排序的三元组顺序表中查找elem对象，或更改或插入
        while (i < this.list.length()) {
            Triple item = this.list.get(i);
            // 若elem存在，则更改改位置矩阵元素
            if (elem.compareTo(item) == 0) {
                // 设置顺序表第i个元素为elem
                this.list.set(i, elem);
                return;
            }

            // elem 较大时向后走
            if (elem.compareTo(item) >= 0) {
                i++;
            }
            else {
                break;
            }
        }
        this.list.insert(i, elem);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int k = 0;

        Triple elem = this.list.get(k++);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++)
                if (elem != null && i == elem.row && j == elem.colum) {
                    str.append(String.format("%-10d", elem.value));
                    elem = this.list.get(k++);
                } else {
                    str.append(String.format("%-10d", 0));
                }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * 返回当前矩阵与MatrixO相加的矩阵， MatrixC=this+MatrixO,不改变当前矩阵，算法同两个多项式相加
     *
     */

    public SeqSparseMatrix plus(SeqSparseMatrix MatrixO) {
        if (this.rows != MatrixO.rows || this.columns != MatrixO.columns) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("两个矩阵阶数不同，不能相加");
            alert.showAndWait();
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
        }

        // 构造rows*columns零矩阵
        SeqSparseMatrix MatrixC = new SeqSparseMatrix(this.rows, this.columns);
        int i = 0, j = 0;
        // 分别遍历两个矩阵的顺序表
        while (i < this.list.length() && j < MatrixO.list.length()) {
            Triple elementA = this.list.get(i);
            Triple elementB = MatrixO.list.get(j);

            // 若两个三元组表示相同位置的矩阵元素，则对应元素值相加
            if (elementA.compareTo(elementB) == 0) {
                // 相加结果不为零，则新建元素
                if (elementA.value + elementB.value != 0)
                    MatrixC.list.addData(new Triple(elementA.row, elementA.colum,
                            elementA.value + elementB.value));

                i++;
                j++;
            } else if (elementA.compareTo(elementB) < 0) { // 将较小三元组复制添加到MatrixC顺序表最后
                // 复制elementA元素执行Triple拷贝构造方法
                MatrixC.list.addData(new Triple(elementA));
                i++;
            } else {
                MatrixC.list.addData(new Triple(elementB));
                j++;
            }
        }

        // 将当前矩阵顺序表的剩余三元组复制添加到MatrixC顺序表最后
        while (i < this.list.length())
            MatrixC.list.addData(new Triple(this.list.get(i++)));

        // 将MatrixO中剩余三元组复制添加到MatrixC顺序表最后
        while (j < MatrixO.list.length()) {
            Triple elem = MatrixO.list.get(j++);
            if (elem != null) {
                MatrixC.list.addData(new Triple(elem));
            }
        }

        return MatrixC;
    }


    public SeqSparseMatrix minus (SeqSparseMatrix MatrixO) {
        if (this.rows != MatrixO.rows || this.columns != MatrixO.columns) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("两个矩阵阶数不同，不能相减");
            alert.showAndWait();
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
        }

        // 构造rows*columns零矩阵
        SeqSparseMatrix MatrixC = new SeqSparseMatrix(this.rows, this.columns);
        int i = 0, j = 0;
        // 分别遍历两个矩阵的顺序表
        while (i < this.list.length() && j < MatrixO.list.length()) {
            Triple elementA = this.list.get(i);
            Triple elementB = MatrixO.list.get(j);

            // 若两个三元组表示相同位置的矩阵元素，则对应元素值相加
            if (elementA.compareTo(elementB) == 0) {
                // 相减结果不为零，则新建元素
                if (elementA.value - elementB.value != 0) {
                    MatrixC.list.addData(new Triple(elementA.row, elementA.colum, elementA.value - elementB.value));
                    System.out.println(elementA.value-elementB.value);
                }

                i++;
                j++;
            } else if (elementA.compareTo(elementB) < 0) { // 将较小三元组复制添加到MatrixC顺序表最后
                // 复制elementA元素执行Triple拷贝构造方法
                MatrixC.list.addData(new Triple(elementA));
                i++;
            } else {
                elementB.value = elementB.value*(-1);
                MatrixC.list.addData(new Triple(elementB));
                j++;
            }
        }

        // 将当前矩阵顺序表的剩余三元组复制添加到MatrixC顺序表最后
        while (i < this.list.length())
            MatrixC.list.addData(new Triple(this.list.get(i++)));

        // 将MatrixO中剩余三元组复制添加到MatrixC顺序表最后
        while (j < MatrixO.list.length()) {
            Triple elem = MatrixO.list.get(j++);
            if (elem != null) {
                elem.value = elem.value*(-1);
                MatrixC.list.addData(new Triple(elem));
            }
        }

        return MatrixC;

    }

    public SeqSparseMatrix multiply (SeqSparseMatrix obj) {
        if (this.columns!=obj.rows) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("A矩阵的列数与B矩阵的行数不相等，不能相乘");
            alert.showAndWait();
            throw new IllegalArgumentException("A矩阵的列数与B矩阵的行数不相等，不能相乘");
        }
        SeqSparseMatrix result = new SeqSparseMatrix(this.rows, obj.columns);
        for (int i=0 ; i<this.rows ; i++) {
            for (int j=0 ; j<obj.columns ; j++) {
                int sum = 0;
                for (int k=0 ; k<this.columns ; k++) {
                    sum+=this.get(i, k)*obj.get(k, j);
                }
                if (sum!=0) {
                    result.list.addData(new Triple(i, j, sum));
                }
            }
        }
        return result;
    }


    /**
     * 比较两个矩阵是否相等
     */

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof SeqSparseMatrix))
            return false;

        SeqSparseMatrix temp = (SeqSparseMatrix) obj;
        return this.rows == temp.rows && this.columns == temp.columns
                && this.list.equals(temp.list);
    }

    /**
     * 返回转置矩阵
     */
    public Matrix.SeqSparseMatrix transpose() {
        //构造零矩阵，指定行数和列数
        Matrix.SeqSparseMatrix trans = new Matrix.SeqSparseMatrix(columns, rows);
        for (int i = 0; i < this.list.getSize(); i++) {
            //插入矩阵对称位置元素的三元组
            trans.set(this.list.findDataByLoc(i).toSymmetry());
        }
        return trans;
    }

}

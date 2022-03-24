package Matrix;

/**
 * 稀疏矩阵的压缩存储
 *
 * 稀疏矩阵非零元素的三元组类
 *
 * @author clarck
 *
 */
public class Triple implements Comparable<Triple> {
    // 行号，列号， 元素值，默认访问权限
    int row, colum, value;

    public Triple(int row, int colum, int value) {
        if (row < 0 || colum < 0) {
            throw new IllegalArgumentException("稀疏矩阵元素三元组的行/列序号非正数");
        }
        this.row = row;
        this.colum = colum;
        this.value = value;
    }


    public Triple(Triple obj) {
        this(obj.row, obj.colum, obj.value);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + colum + ", " + value + ")";
    }

    /**
     * 两个三元组是否相等，比较位置和元素值
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Triple))
            return false;
        Triple elem = (Triple) obj;
        return this.row == elem.row && this.colum == elem.colum && this.value == elem.value;
    }

    /**
     * 根据三元组位置比较两个三元组的大小，与元素值无关，约定三元组排序次序
     */
    @Override
    public int compareTo(Triple obj) {
        //当前三元组对象小
        if (this.row < obj.row || this.row == obj.row && this.colum < obj.colum)
            return -1;

        //相等，与equals方法含义不同
        if (this.row == obj.row && this.colum == obj.colum)
            return 0;

        //当前三元组对象大
        return 1;
    }


    public void add(Triple obj) {
        if (this.compareTo(obj) == 0)
            this.value += obj.value;
        else
            throw new IllegalArgumentException("两项的指数不同，不能相加");
    }

    /**
     * 返回对称位置矩阵元素的三元组
     */
    public Triple toSymmetry() {
        return new Triple(this.colum, this.row, this.value);
    }

    /**
     * 加法运算，重载运算符+
     */
    public Triple plus(Triple obj) {
        Triple MatrixC = new Triple(this);
        MatrixC.add(obj);
        return MatrixC;
    }
}
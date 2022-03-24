package Matrix;
/**
 * 该顺序表实现仅针对不存在相同元素的集合
 * 若集合中存在相同元素，则：
 *      方法 deleteDataByData(Type)：删除第一个满足要求的数据。
 *      方法 findLocationOfData(Type)：返回查找到的满足要求的第一个数据的地址。
 */
public class SeqList <Type> {
    /**
     * 定义数据结构
     */
    private static final int MAXSIZE = 100;//顺序表的最大长度
    private int size;//顺序表中的节点数
    Type[] dataList;//保存节点类型为 Type 的数组

    /**
     * 默认构造方法
     */
    public SeqList(){
        dataList = (Type[]) new Object[MAXSIZE];
        this.size = 0;
    }

    /**
     * 获取顺序表的大小
     * @return size：顺序表大小
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 向顺序表末尾添加节点 data
     * @param data ：新添加的节点
     */
    public void addData(Type data){
        if (this.size == MAXSIZE) {//首先判断顺序表是否已满
            //System.out.println("顺序表已满！");
            return;
        }
        dataList[size++] = data;
        //顺序表未满，则将新数据添加到顺序表末尾。
        // 同时顺序表大小递增 1
    }

    /**
     * 向指定位置插入节点 data
     * @param data：新插入的数据
     * @param loc：插入的位置
     * @return -1：位置超出范围或顺序表满，插入错误
     *          0：插入成功
     */
    public int insertDataByLoc(Type data, int loc){
        if ((size == MAXSIZE)){
            //System.out.println("  顺序表已满！");
            return -1;
        }else if ((loc >= MAXSIZE)){
            //System.out.println("  指定位置过大！请指定 " + MAXSIZE + " 以内的位置！");
            return -1;
        } else if (loc < 0){
            //System.out.println("  指定位置不正确！位置不能为负数！");
            return -1;
        } else if (loc >= size){
            addData(data);
            //System.out.println("  指定位置超过当前顺序表最后一个节点的位置！\n  已将当前数据插入到顺序表最后！");
            return 0;
        }else {
            //将 loc 往后的节点都向后移动一个位置
            System.arraycopy(dataList, loc, dataList, loc + 1, size - loc);
            dataList[loc] = data;//将新数据插入到指定位置
            this.size += 1;//将顺序表大小递增 1
            return 0;
        }
    }

    /**
     * 删除指定位置的数据
     * @param loc ：数据的位置
     */
    public void deleteDataByLoc(int loc){
        Type data = findDataByLoc(loc);//将 loc 处的数据保存下来
        if (data == null)//数据为空
            return;
        //将 loc 往后的数据顺序地往前移动一个位置
        if (this.size - loc >= 0) System.arraycopy(dataList, loc + 1, dataList, loc, this.size - loc);
        this.size --;//将顺序表大小递减 1
    }

    /**
     * 查找指定位置的数据
     * @param loc：数据的位置
     * @return 指定位置的数据值
     */
    public Type findDataByLoc(int loc){
        if ((loc > this.size) || (loc < 0) || (dataList == null))
            return null;
        return dataList[loc];
    }

    public Type get(int k) {
        if ((k > this.size) || (k < 0) || (dataList == null))
            return null;
        return dataList[k];
    }

    public int length() {
        return this.size;
    }

    public void set(int i, Type obj) {

    }

    public void insert(int loc, Type obj) {
        if ((size == MAXSIZE)){
            //System.out.println("  顺序表已满！");

        }else if ((loc >= MAXSIZE)){
            //System.out.println("  指定位置过大！请指定 " + MAXSIZE + " 以内的位置！");

        } else if (loc < 0){
            //System.out.println("  指定位置不正确！位置不能为负数！");

        } else if (loc >= size){
            addData(obj);
            //System.out.println("  指定位置超过当前顺序表最后一个节点的位置！\n  已将当前数据插入到顺序表最后！");

        }else if (size == 0) {
            dataList[0] = obj;

        }else {
            //将 loc 往后的节点都向后移动一个位置
            System.arraycopy(dataList, loc, dataList, loc + 1, size - loc);
            dataList[loc] = obj;//将新数据插入到指定位置
            this.size += 1;//将顺序表大小递增 1

        }
    }
}
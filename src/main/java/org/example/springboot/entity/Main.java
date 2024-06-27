package org.example.springboot.entity;

import java.io.FileNotFoundException;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {

    public static void main(String[] args) throws Exception {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        ChuLiDanCi DC=null;

        try{
            DC=new ChuLiDanCi();
        }catch ( FileNotFoundException e ){
            System.out.printf("文件未找到\n");
        }
        DC.XieDanCi();
        /*
        try{
            System.out.printf("%d\n",DC.half(new String("word")));
        }catch ( Exception e ){
            e.printStackTrace();
        }*/
        Learning ML=new Learning(DC);
        ML.xunLian();
        ML.test();
    }
}
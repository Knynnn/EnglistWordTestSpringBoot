package org.example.springboot.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

import static java.lang.Math.max;

public class Learning {
    final int DCS=6891;

    Scanner sr;
    ChuLiDanCi DC;
    public double QZ[][];
    public int SJFS=0;
    public int SJ[][][],FS[];//SJ数组 [第i组数据][单词id][0为会的，1为不会的]，FS为单词数量
    public Learning(ChuLiDanCi DC) throws Exception {
        this.DC=DC;

        QZ=new double[12000][3];
        for (int i=0;i<12000;i++) {
            QZ[i][0] = 100;
            QZ[i][1] = -10;
        }

        SJ=new int[1200][1000][2];
        FS=new int[1200];
        String path = this.getClass().getClassLoader().getResource("").getPath();
        //System.out.printf("%s\n",path);
        path=path+"data.txt";
        File file = new File(path);
        //System.out.printf("%s\n",path);
        sr = new Scanner(file);

        while (sr.hasNextLine())
        {
            SJFS++;
            String ss=sr.nextLine();
            for (String dc:ss.split(",")){
                int id=DC.half(dc);
                SJ[SJFS][0][0]++;
                SJ[SJFS][ SJ[SJFS][0][0] ][0]=id;
            }

            ss=sr.nextLine();
            for (String dc:ss.split(",")){
                int id=DC.half(dc);
                SJ[SJFS][0][1]++;
                SJ[SJFS][ SJ[SJFS][0][1] ][1]=id;
            }

            ss=sr.nextLine();
            FS[SJFS]=Integer.parseInt(ss);
        }


        sr.close();

    }

    public void xunLian(){
        double LR=33.33;
        double loss= 0.0;
        for (int epoch=1;epoch<=3333;epoch++){
            LR-=0.01;
            loss=0.0;
            for (int d=1;d<=SJFS;d++){
                double ygz=0;
                for (int i=1;i<=SJ[d][0][0];i++){
                    ygz+=QZ[ SJ[d][i][0] ][0];
                }

                for (int i=1;i<=SJ[d][0][1];i++){
                    ygz+=QZ[ SJ[d][i][1] ][1];
                }
                double pc=ygz-FS[d];
                loss+=pc;
                pc=pc/(SJ[d][0][0]+SJ[d][1][1]);
                //System.out.printf("%f\n",pc);


                for (int i=1;i<=SJ[d][0][0];i++){
                    QZ[ SJ[d][i][0] ][0]=QZ[ SJ[d][i][0] ][0]-pc*LR;
                    //System.out.printf("pc:%f\n",pc*LR);
                }

                for (int i=1;i<=SJ[d][0][1];i++) {
                    QZ[ SJ[d][i][1] ][1]=QZ[ SJ[d][i][1] ][1]-pc*LR*0.1;
                }

            }
            //System.out.printf("%f %f\n",loss,LR);
        }
    }

    public void test() throws Exception {
        while (true){

            System.out.printf("请输入测试样例，分两行，分别为会的单词和不会的单词，以逗号分隔：\n");
            sr = new Scanner(System.in);
            double jg=0.0;
            String ss=sr.nextLine();

            for (String dc:ss.split(",")){
                int id=DC.half(dc);
                jg+=QZ[id][0];
            }
            ss=sr.nextLine();

            for (String dc:ss.split(",")){
                int id=DC.half(dc);
                jg+=QZ[id][1];
            }

            System.out.printf("%d\n",max(50,(int)jg));
        }
    }

    public int output(List<String> Hui, List<String> BuHui) throws Exception {
        double jg=0.0;
        for (String ss: Hui){
            int id=DC.half(ss);
            jg+=QZ[id][0];
        }
        for (String ss: BuHui){
            int id=DC.half(ss);
            jg+=QZ[id][1];
        }
        return max((int)jg,50);
    }
}

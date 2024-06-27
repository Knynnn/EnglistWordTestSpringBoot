package org.example.springboot.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChuLiDanCi {
    final int DCS=6891;
    public String DanCi[];
    Set<String> DCK;


    Scanner sr;
    public ChuLiDanCi() throws FileNotFoundException {
        DanCi=new String[12000];

        String path = this.getClass().getClassLoader().getResource("").getPath();
        //System.out.printf("%s\n",path);
        path=path+"kaoyan.txt";
        File file = new File(path);
        //System.out.printf("%s\n",path);
        sr = new Scanner(file);
        for (int i=1;i<=DCS;i++){

            String ss=sr.nextLine();
            //System.out.printf("%s",ss);
            ss = ss.substring(1, ss.length() - 1);
            DanCi[i]=ss;
        }
        for (int i=1;i<=DCS;i++){
            for (int j=i+1;j<=DCS;j++){
                int BJ=DanCi[i].compareTo(DanCi[j]);
                if (BJ>0){
                    String t=DanCi[i];
                    DanCi[i]=DanCi[j];
                    DanCi[j]=t;
                }
            }
        }
        sr.close();
    }

    public int half(String s) throws Exception
    {
        int l=1,r=DCS;
        while (l<=r){
            int mid=(l+r)/2;
            int BJ=s.compareTo(DanCi[mid]);

            if (BJ>0){
                l=mid+1;
            }else if (BJ<0) {
                r = mid - 1;
            }else{
                return mid;
            }
        }
        throw new Exception("错误，找不到该单词");
    }

    public void XieDanCi() throws FileNotFoundException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        //System.out.printf("%s\n",path);
        path=path+"data.txt";
        File file = new File(path);
        //System.out.printf("%s\n",path);
        sr = new Scanner(file);

        DCK=new HashSet<>();
        for (int i=1;i<=DCS;i++)DCK.add(DanCi[i]);
        Boolean bk=true;
        while (sr.hasNextLine())
        {
            String ss=sr.nextLine();
            for (String dc:ss.split(",")){
                if (DCK.contains(dc)==false){
                    if (bk){System.out.printf("有以下单词未被包括：\n");bk=false;}
                    DCK.add(dc);
                    System.out.printf("\"%s\"\n",dc);
                }
            }
            ss=sr.nextLine();
            for (String dc:ss.split(",")){
                if (DCK.contains(dc)==false){
                    if (bk){System.out.printf("有以下单词未被包括：\n");bk=false;}
                    DCK.add(dc);
                    System.out.printf("\"%s\"\n",dc);
                }
            }
            ss=sr.nextLine();
        }

    }
}

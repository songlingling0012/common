package com.song.learn.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/6/10 16:42
 */
public class SparkDemo {


    public <T> T get(){

        return null;
    }

    public static void main(String[] args) {

        SparkConf conf = new SparkConf();

        SparkContext spark = new SparkContext(conf);

        SparkSession session = SparkSession.builder().appName("").master("").getOrCreate();


    }

}

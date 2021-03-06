package com.song.learn.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Author: Song_
  * @Description:
  * @Date: 2021/6/10 16:53
  */
object SparkDemoTest {

  def main(args: Array[String]): Unit = {

    def getData[T](list:List[T]) = {
       list(list.length/2)
    }

    implicit def double2Int(f:Double)=f.toInt

    var i:Int=3.14159

    val conf: SparkConf = new SparkConf()
    conf.setAppName(this.getClass.getSimpleName).setMaster("local[*]")

    val sparkContext: SparkContext = new SparkContext(conf)

    val testRDD: RDD[String] = sparkContext.makeRDD(List("a","b","c"))

    testRDD.foreach(println)






  }

}

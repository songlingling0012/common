package com.song.learn.bigdata.spark

/**
  * @Author: Song_
  * @Description:
  * @Date: 2021/6/15 18:00
  */
object ScalaBasicDemo {

  def main(args: Array[String]): Unit = {

    //求平均值
    val d1 =  Array (("bj",28.1), ("sh",28.7), ("gz",32.0), ("sz", 33.1))
    val d2 =  Array (("bj",27.3), ("sh",30.1), ("gz",33.3))
    val d3 =  Array (("bj",28.2), ("sh",29.1), ("gz",32.0), ("sz", 30.5))
    //把需要的数据组装到一起
    val data1:Array[(String,Double)]=d1.union(d2).union(d2)//d1 union d2 union d3

    val data2: Map[String, Double] = data1.toMap


    System.exit(0)

    val list: List[Int] = List(1,2,3,4,5)

    val reduced: Int = list.reduceLeft((x, y)=>{x+y})

    println(reduced+"=====")


    val folded: Int = list.fold(0)((x, y)=>{x+y})

    println(folded+"=====")

  }
}
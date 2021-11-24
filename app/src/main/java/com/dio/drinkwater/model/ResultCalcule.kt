package com.dio.drinkwater.model

class ResultCalcule {

    private val mlYoung = 40.0
    private val mlAdult = 35.0
    private val mlOld = 30.0
    private val mlOlder = 25.0

    private var resultMl = 0.0
    private var resultTotal = 0.0

    fun totalCalculeMl(pound: Double, age: Int){

        if (age <= 17){
            resultMl = pound * mlYoung
            resultTotal = resultMl

        }else if (age <= 55){
            resultMl = pound * mlAdult
            resultTotal = resultMl

        }else if (age <= 65){
            resultMl = pound * mlOld
            resultTotal = resultMl

        }else{
            resultMl = pound * mlOlder
            resultTotal = resultMl
        }

    }

    fun resultMl(): Double{
        return  resultTotal
    }

}
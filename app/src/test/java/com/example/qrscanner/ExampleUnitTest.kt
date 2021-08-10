package com.example.qrscanner

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val car: Car = Car()

    @Test
    fun useCar() {
        car.gear.changeGear(gear = 1)
        car.go()
        car.stop()

        car.handle.changeDirection(-1)
        car.go()

        car.gear.changeGear(gear = 3)
        car.go()

        car.handle.changeDirection(1)
        car.go()
    }

}

/*

사용자 -> 대표님

자동차
1. 엔진
2. 기어
3. 핸들

엔진 << 사용자가 건드릴 수 없음.
1. 자동차를 앞으로 이동시킨다.
2. 기어가 변화하면 속도를 변화시킨다.

기어
1. 사용자로부터 1~3 단 까지의 값을 입력받는다.
2. 기어가 변화하면 엔진의 속도가 변화한다.

핸들
1. 사용자로부터 좌/우 값을 입력받는다.

 */

class Car {

    private val engine: Engine = Engine()

    val gear: Gear = Gear(engine)

    val handle: Handle = Handle()

    fun go() {
        // 정면, 좌, 우
        // 0, -1 , 1
        when (handle.direction) {
            0 -> {
                print("정면 방향으로 갑니다.\n")
            }
            -1 -> {
                print("좌측 방향으로 갑니다.\n")
            }
            1 -> {
                print("우측 방향으로 갑니다.\n")
            }
            else -> {
                print("잘못된 방향입니다.\n")
            }
        }
        engine.forward()
    }

    fun stop() {
        gear.changeGear(gear = 0)
        print("자동차가 멈춥니다.\n")
    }

}



class Engine {

    // public int speed = 0;
    // var < 가변
    // val < 불변
    // (공개 정도) (var / val) (변수 이름) : (타입)
    private var speed : Int = 0;


    // 1. 자동차를 앞으로 이동시킨다.
    fun forward() {
        if(speed <= 0) {
            print("현재 속도가 0이네요. 기어를 올려주세요.\n")
        } else {
            print("앞으로 갑시다. 현재 속도 = $speed km/h.\n")
        }
    }

    // 2. 기어가 변화하면 속도를 기어에 맞게 변화시킨다.
    // > y = x * 30
    fun changeSpeed(gear: Int) {
        speed = gear * 30
        print("속도가 ${gear * 30}으로 변화합니다.\n")
    }
}


class Gear(private val engine: Engine) {

    // (타입)? << 이 타입이 null 이 될 수 있다
    // (타입) << 이 값은 절대 비어있을 수 없다
    var gear: Int? = null

    // 1. 사용자로부터 1~3 단 까지의 값을 입력받는다.
    // 2. 기어가 변화하면 엔진의 속도가 변화한다.
    // fun 함수 이름 (파라미터1: 타입, 파라미터2: 타입, ... ) { ... }
    fun changeGear(gear: Int?) {
        // 함수 바깥에 gear
        // 함수 파라미터로 gear
        this.gear = gear

        // 여기서 엔진을 바꿔줘야 함.
        if(gear != null) {
            engine.changeSpeed(gear)
        } else {
            print("기어가 해제되었습니다.\n")
        }
    }
}


class Handle {

    // 정면, 좌, 우
    // 0, -1 , 1
    var direction: Int = 0

    fun changeDirection(direction: Int) {
        this.direction = direction
    }

}




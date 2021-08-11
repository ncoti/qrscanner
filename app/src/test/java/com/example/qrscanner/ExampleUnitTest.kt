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
    fun usCar() {
        car.gear.changeGear( gear= 1)
        car.go()
        car.stop()

        car.handle.changeDirection(direction = -1)
        car.go()
        car.gear.changeGear(gear = 3)
        car.go()

        car.handle.changeDirection(direction = 1)
        car.go()
    }

}

class Car {

    private val engine: Engine = Engine()

    val gear: Gear = Gear(engine)

    val handle: Handle = Handle()

    fun go() {

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
    fun stop(){
        gear.changeGear(gear = 0)
        print("자동차가 멈춥니다.\n")
    }
}

class Engine{

    //public int speed = 0;
    //var 가변 / val 불변
    // (공개정도) (var / val) (변수이름) : (타입)
    private var speed: Int = 0;

    //1.자동차를 앞으로 이동시킨다.
    fun forward() {
        if(speed <= 0) {
           print("현재 속도가 0이네요. 기어를 올려주세요.\n")
        }else {
            print("앞으로 갑니다. 현재 속도 = $speed km/h\n")
        }


    }

    //2. 기어가 변화하면 속도를 기어에 맞게 변화시킨다.
    //> y = x*30
    fun changeSpeed(gear:Int){
        speed = gear *30

        print("속도가 ${gear*30} 변화합니다.\n")
    }

}
class  Gear(private val engine: Engine){

    var gear: Int? = null

    fun changeGear(gear: Int?) {
        this.gear = gear

        if(gear !=null){
            engine.changeSpeed(gear)
        } else {
            print ("기어가 해제되었습니다.\n")
        }
    }

}

class Handle {
    var direction: Int = 0

    fun changeDirection(direction: Int){
        this.direction = direction
    }
}
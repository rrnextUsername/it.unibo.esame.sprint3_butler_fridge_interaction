package tests

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlinx.coroutines.GlobalScope
import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import it.unibo.kactor.sysUtil
import org.junit.jupiter.api.AfterEach
import it.unibo.kactor.MsgUtil
  
class TestNegativeReply {
  var resource : ActorBasic? = null
  var fridgeDummy : ActorBasic? = null
  var frontEndDummy : ActorBasic? = null
	
	@BeforeEach
	fun systemSetUp() {
  	 		GlobalScope.launch{ //activate an observer ...
 				itunibo.coap.observer.main()		//blocking
 			}	
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestButler starts fridge mind ")
				it.unibo.ctxButler.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("butler")	
			fridgeDummy = sysUtil.getActor("fridge_dummy")
			frontEndDummy = sysUtil.getActor("frontend_dummy")	
		    println(" %%%%%%% TestButler getActors resource=${resource}")
 	}
 
	@AfterEach
	fun terminate() {
		println(" %%%%%%% TestButler terminate ")
	}
 
	@Test
	fun queryFoodTest() {
		println(" %%%%%%% TestButler  queryFoodTest with food present")
		sendCmdMessage(resource!!,"panzerotto",5000)
		solveCheckGoal(fridgeDummy!!,"done(queryReply, panzerotto)")
		delay(3000)
		solveCheckGoal(frontEndDummy!!,"missingFood(panzerotto)")
		solveCheckGoalFalse(resource!!,"done(check, 1)")
		solveCheckGoal(resource!!,"stato(waitCmd)")
		
 	}
	
//----------------------------------------
	
	fun sendCmdMessage( actor : ActorBasic, name : String, time : Long ){
		actor.scope.launch{
			println("--- sendCmdMessage cmd(addFood,$name)")
  			MsgUtil.sendMsg("cmd","cmd(addFood,$name)",actor)
 		}
		delay(time) //give time to do the move
  	}

 	
	fun solveCheckGoal( actor : ActorBasic, goal : String ){
		actor.solve( goal  )
		var result =  actor.resVar
		println(" %%%%%%%  actor={$actor.name} goal= $goal  result = $result")
		assertTrue(result=="success","%%%%%%% TestFridge expected 'success' found $result")
	}
	
	fun solveCheckGoalFalse( actor : ActorBasic, goal : String ){
		actor.solve( goal  )
		var result =  actor.resVar
		println(" %%%%%%%  actor={$actor.name} goal= $goal  result = $result")
		assertTrue(result=="fail","%%%%%%% TestFridge expected 'fail' found $result")
	}

	fun delay( time : Long ){
		Thread.sleep( time )
	}


}

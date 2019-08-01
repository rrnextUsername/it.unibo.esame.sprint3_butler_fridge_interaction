/* Generated by AN DISI Unibo */ 
package it.unibo.frontend_dummy

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Frontend_dummy ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						solve("consult('sysRules.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="waitEvent", cond=doswitch() )
				}	 
				state("waitEvent") { //this:State
					action { //it:State
					}
					 transition(edgeName="t01",targetState="persistMissingFood",cond=whenEvent("missingFood"))
				}	 
				state("persistMissingFood") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("missingFood(FOOD)"), Term.createTerm("missingFood(FOOD)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("$name in ${currentState.stateName} | $currentMsg")
								solve("assert(missingFood(${payloadArg(0)}))","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="waitEvent", cond=doswitch() )
				}	 
			}
		}
}

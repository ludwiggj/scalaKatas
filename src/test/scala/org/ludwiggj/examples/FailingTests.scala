package org.ludwiggj.examples

import utest._

object FailingTests extends TestSuite {
  val tests = Tests {
    'outer1 - {
      throw new Exception("Outer One")
      'inner1 - {
        throw new Exception("Inner One")
      }
      'inner2 - {
        throw new Exception("Inner One")
      }
    }
    'outer2 - {
      throw new Exception("Outer Two")
      'inner3 - {
        throw new Exception("Inner Two")
      }
    }
  }
}
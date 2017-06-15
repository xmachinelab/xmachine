package com.blacklight.xmachine.index;

import java.util.concurrent.atomic.AtomicLong;

public class ProgramIndex {

  private static final AtomicLong counter = new AtomicLong(10000001);

  public static long nextValue() {
    return counter.getAndIncrement();
  }
}
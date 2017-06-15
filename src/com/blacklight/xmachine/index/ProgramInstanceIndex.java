package com.blacklight.xmachine.index;

import java.util.concurrent.atomic.AtomicLong;

public class ProgramInstanceIndex {

  private static final AtomicLong counter = new AtomicLong(1);

  public static long nextValue() {
    return counter.getAndIncrement();
  }
}
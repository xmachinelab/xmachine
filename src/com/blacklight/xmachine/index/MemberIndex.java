package com.blacklight.xmachine.index;

import java.util.concurrent.atomic.AtomicLong;

import com.blacklight.xmachine.fundamentals.XMember;

public class MemberIndex {

  private final AtomicLong counter = new AtomicLong(1);

  public long nextValue(XMember xmember) {
    return xmember.getBase() +  counter.getAndIncrement();
  }
}
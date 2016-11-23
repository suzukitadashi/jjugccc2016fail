/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package jjug.ccc;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@Warmup(iterations=3)
@Measurement(iterations=3)
@Fork(2)
public class MyBenchmark {

	private List<String> strings = new ArrayList<>(count);
	private static final int count = 5000;

	@Setup
	public void init() {
		strings = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			strings.add("[Str" + String.format("%1$03d", i) + "]");
		}
	}
	
	@Benchmark
	public void test1String() {
		concat1String(strings);
	}
	
	@Benchmark
	public void test2StringConcat() {
		concat2StringConcat(strings);
	}
	
	@Benchmark
	public void test3StringBufferConcat() {
		concat3StringBuffer(strings);
	}
	
	@Benchmark
	public void test4StringBuilder() {
		concat4StringBuilder(strings);
	}

	public String concat1String(List<String> strs) {
		String strings = "";
		for (String str : strs) {
			strings += str;
			strings += "\n";
		}
		return strings;
	}

	public String concat2StringConcat(List<String> strs) {
		String strings = "";
		for (String str : strs) {
			strings = strings.concat(str);
			strings = strings.concat("\n");
		}
		return strings;
	}


	public String concat3StringBuffer(List<String> strs) {
		StringBuffer strings = new StringBuffer();
		for (String str : strs) {
			strings.append(str);
			strings.append("\n");
		}
		return strings.toString();
	}


	public String concat4StringBuilder(List<String> strs) {
		StringBuilder strings = new StringBuilder();
		for (String str : strs) {
			strings.append(str);
			strings.append("\n");
		}
		return strings.toString();
	}

}

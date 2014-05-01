package com.insightfullogic.java_final_benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.CompilerControl.Mode.DONT_INLINE;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Thread;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1, timeUnit = SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = SECONDS)
@Fork(1)
@OutputTimeUnit(NANOSECONDS)
@State(Thread)
public class JavaFinalBenchmark {

    private TargetClass1 target;
    private InlinableTargetClass1 inlinableTarget;

    @Setup
    public void setup() {
        target = new TargetClass1();
        inlinableTarget = new InlinableTargetClass1();
    }

    @GenerateMicroBenchmark
    public void virtualInvoke() {
        target.targetVirtual();
    }

    @GenerateMicroBenchmark
    public void finalInvoke() {
        target.targetFinal();
    }

    @GenerateMicroBenchmark
    public void inlinableVirtualInvoke() {
        inlinableTarget.targetVirtual();
    }

    @GenerateMicroBenchmark
    public void inlinableFinalInvoke() {
        inlinableTarget.targetFinal();
    }

    /**
     * Inherited Methods
     * <p/>
     * Test the hypothesis of distance up the class hierarchy affects the invoke performance.
     * Numbers refer to how far up the class hierarchy the inherited method is from
     */

    @GenerateMicroBenchmark
    public void parentMethod1() {
        target.inheritedTarget1();
    }

    @GenerateMicroBenchmark
    public void parentMethod2() {
        target.inheritedTarget2();
    }

    @GenerateMicroBenchmark
    public void parentMethod3() {
        target.inheritedTarget3();
    }

    @GenerateMicroBenchmark
    public void parentMethod4() {
        target.inheritedTarget4();
    }

    @GenerateMicroBenchmark
    public void parentFinalMethod1() {
        target.inheritedFinalTarget1();
    }

    @GenerateMicroBenchmark
    public void parentFinalMethod2() {
        target.inheritedFinalTarget2();
    }

    @GenerateMicroBenchmark
    public void parentFinalMethod3() {
        target.inheritedFinalTarget3();
    }

    @GenerateMicroBenchmark
    public void parentFinalMethod4() {
        target.inheritedFinalTarget4();
    }

    @GenerateMicroBenchmark
    public void alwaysOverriddenMethod() {
        target.alwaysOverriddenTarget();
    }

    @GenerateMicroBenchmark
    public void inlinableParentMethod1() {
        inlinableTarget.inheritedTarget1();
    }

    @GenerateMicroBenchmark
    public void inlinableParentMethod2() {
        inlinableTarget.inheritedTarget2();
    }

    @GenerateMicroBenchmark
    public void inlinableParentMethod3() {
        inlinableTarget.inheritedTarget3();
    }

    @GenerateMicroBenchmark
    public void inlinableParentMethod4() {
        inlinableTarget.inheritedTarget4();
    }

    @GenerateMicroBenchmark
    public void inlinableParentFinalMethod1() {
        inlinableTarget.inheritedFinalTarget1();
    }

    @GenerateMicroBenchmark
    public void inlinableParentFinalMethod2() {
        inlinableTarget.inheritedFinalTarget2();
    }

    @GenerateMicroBenchmark
    public void inlinableParentFinalMethod3() {
        inlinableTarget.inheritedFinalTarget3();
    }

    @GenerateMicroBenchmark
    public void inlinableParentFinalMethod4() {
        inlinableTarget.inheritedFinalTarget4();
    }

    @GenerateMicroBenchmark
    public void inlinableAlwaysOverriddenMethod() {
        inlinableTarget.alwaysOverriddenTarget();
    }


    public static class InlinableTargetClass1 extends InlinableTargetClass2 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget1() {
        }

        public final void inheritedFinalTarget1() {
        }

        public void targetVirtual() {
        }

        public final void targetFinal() {
        }
    }

    public static class InlinableTargetClass2 extends InlinableTargetClass3 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget2() {
        }

        public final void inheritedFinalTarget2() {
        }
    }

    public static class InlinableTargetClass3 extends InlinableTargetClass4 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget3() {
        }

        public final void inheritedFinalTarget3() {
        }
    }

    public static class InlinableTargetClass4 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget4() {
        }

        public final void inheritedFinalTarget4() {
        }
    }

    @CompilerControl(DONT_INLINE)
    public static class TargetClass1 extends TargetClass2 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget1() {
        }

        public final void inheritedFinalTarget1() {
        }

        public void targetVirtual() {
        }

        public final void targetFinal() {
        }
    }

    @CompilerControl(DONT_INLINE)
    public static class TargetClass2 extends TargetClass3 {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget2() {
        }

        public final void inheritedFinalTarget2() {
        }
    }

    @CompilerControl(DONT_INLINE)
    public static class TargetClass3 extends TargetClass {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget3() {
        }

        public final void inheritedFinalTarget3() {
        }
    }

    @CompilerControl(DONT_INLINE)
    public static class TargetClass {
        public void alwaysOverriddenTarget() {
        }

        public void inheritedTarget4() {
        }

        public final void inheritedFinalTarget4() {
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JavaFinalBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}

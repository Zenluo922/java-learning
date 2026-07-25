package Excise_Pool;

import java.util.concurrent.Callable;

public class MyString implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "你好";
    }
}

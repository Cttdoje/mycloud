package com.cttdoje.user.utils;

public class RedisLock {

    private String key;
    private String value;
    private int expire;

    public RedisLock(String key, int expire) {
        this.key = key;
        this.expire = expire;
    }

    public boolean lock() {
        Object lockValue = RedisUtil.get(this.key);
        if(lockValue != null){
            return false;
        }
        this.value = String.valueOf(System.currentTimeMillis());
        return RedisUtil.set(this.key, this.value, expire);
    }

    public boolean tryLock(long timeOut) {
        this.value = String.valueOf(System.currentTimeMillis());
        if(timeOut < 0){
            return false;
        }
        final long deadline = System.currentTimeMillis() + (timeOut * 1000);
        System.out.println("key= "+ this.key + " value= "+this.value);
        try {
            boolean lockResult;
            for(;;){
                lockResult = RedisUtil.set(this.key, this.value, expire);
                if(lockResult){
                    System.out.println("try lock success");
                    return true;
                }
                timeOut = deadline - System.currentTimeMillis();
                if(timeOut <= 0L){
                    System.out.println("try lock time out...");
                    return false;
                }
            }
        }catch (Exception e){
            System.out.println("try lock error...");
            return false;
        }

    }

    public boolean unLock() {
        Object value = RedisUtil.get(this.key);
        if(value == null){
            return false;
        }else{
            if(value instanceof String){
                if(this.value.equals(value)){
                    return RedisUtil.remove(this.key);
                }
            }else{
                System.out.println("解锁异常...");
                return false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(System.nanoTime());
    }

}

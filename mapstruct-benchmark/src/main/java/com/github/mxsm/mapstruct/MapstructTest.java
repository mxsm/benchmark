package com.github.mxsm.mapstruct;

public class MapstructTest {
    public static void main(String[] args) {
        User user = new User();
        user.setAddress("广东省");
        user.setAge((short) 10);
        user.setEmail("ljbmxsm@gmail.com");
        user.setName("mxsm");
        user.setPassword("mxsm");

        UserEntity userEntity = UserMapper.INSTANCE.convertUser2UserEntity(user);
        System.out.println(userEntity);
    }
}

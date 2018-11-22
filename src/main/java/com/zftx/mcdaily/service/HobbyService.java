package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Hobby;
import com.zftx.mcdaily.bean.User;

import java.util.List;

public interface HobbyService {

    /**
     * 查   兴趣
     * @param hobby
     * @return
     */
    public List<Hobby> getHobby(Hobby hobby);

    /**
     * 增   兴趣
     * @param hobby
     * @return
     */
    public String insertHobby(Hobby hobby);

    /**
     * 修   兴趣
     * @param hobby
     * @return
     */
    public String updateHobby(Hobby hobby);

    /**
     * 实际 删 兴趣
     * @param hobby
     * @return
     */
    public String deleteHobby(Hobby hobby);
}

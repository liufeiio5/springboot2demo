package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Line;
import com.zftx.mcdaily.mapper.LineMapper;
import com.zftx.mcdaily.service.LineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineMapper lineMapper;

    @Override
    public List<Line> findLineAll(Line line) {
        return lineMapper.findLineAll(line);
    }

    @Override
    public Integer addLine(Line line) {
        return lineMapper.addLine(line);
    }

    @Override
    public Integer updateLine(Line line) {
        return lineMapper.updateLine(line);
    }

    @Override
    public Integer delLine(Line line) {
        return lineMapper.delLine(line);
    }
}

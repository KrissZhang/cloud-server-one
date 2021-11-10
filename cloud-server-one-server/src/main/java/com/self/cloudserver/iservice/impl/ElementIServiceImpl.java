package com.self.cloudserver.iservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.cloudserver.excel.bean.Element;
import com.self.cloudserver.iservice.ElementIService;
import com.self.cloudserver.mapper.ElementMapper;
import org.springframework.stereotype.Service;

@Service
public class ElementIServiceImpl extends ServiceImpl<ElementMapper, Element> implements ElementIService {

}

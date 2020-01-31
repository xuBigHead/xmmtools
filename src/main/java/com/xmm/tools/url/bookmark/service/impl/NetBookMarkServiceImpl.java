package com.xmm.tools.url.bookmark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmm.tools.common.result.R;
import com.xmm.tools.url.bookmark.entity.NetBookMarkType;
import com.xmm.tools.url.bookmark.entity.NetBookmark;
import com.xmm.tools.url.bookmark.entity.query.NetBookMarkQuery;
import com.xmm.tools.url.bookmark.mapper.NetBookmarkMapper;
import com.xmm.tools.url.bookmark.service.NetBookMarkService;
import com.xmm.tools.url.bookmark.service.NetBookMarkTypeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xmm
 * @since 2019-09-10
 */
@Service
public class NetBookMarkServiceImpl extends ServiceImpl<NetBookmarkMapper, NetBookmark> implements NetBookMarkService {
    @Autowired
    NetBookMarkTypeService bookMarkTypeService;
    @Override
    public void bookMarkByPage(Page<NetBookmark> netBookMarkPage, NetBookMarkQuery query) {
        QueryWrapper<NetBookmark> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        // 获取query中的条件
        String name = query.getName();
        String typeId = query.getTypeId();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(typeId)) {
            queryWrapper.eq("type_id", typeId);
        }
        // 根据条件查询
        baseMapper.selectPage(netBookMarkPage, queryWrapper);
    }

    @Override
    public void removeBookMarkOfType(String typeId) {
        QueryWrapper<NetBookmark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void importBookMarkFile(MultipartFile file) {
        File toFile = null;
        InputStream ins = null;
        String bookMarkTypeId = "";
        try {
            ins = file.getInputStream();
            toFile = new File(file.getName());
            inputStreamToFile(ins, toFile);
            Document document = Jsoup.parse(toFile, "UTF-8");
            Elements contents = document.getElementsByTag("DT");
            boolean flag = false;
            for (Element content : contents) {
                boolean bookMarkTypeFlag = false;
                NetBookMarkType bookMarkType = new NetBookMarkType();
                Elements types = content.getElementsByTag("H3");
                for (Element type : types) {
                    String linkText = type.text();
                    if("书签栏".equals(linkText) || "".equals(linkText)){
                        flag = true;
                        break;
                    }
                    bookMarkType.setType(linkText);
                    bookMarkTypeFlag = true;
                }
                if(flag){
                    flag = false;
                    continue;
                }else {
                    if(bookMarkTypeFlag) {
                        Elements bookMarks = content.getElementsByTag("A");
                        bookMarkType.setAmount(bookMarks.size());
                        bookMarkTypeService.save(bookMarkType);
                        bookMarkTypeId = bookMarkType.getId();
                        for (Element bookMark : bookMarks) {
                            NetBookmark netBookMark = new NetBookmark();
                            String linkHref = bookMark.attr("href");
                            String linkText = bookMark.text();
                            netBookMark.setNetUrl(linkHref);
                            if (linkText.length() > 50) {
                                linkText = linkText.substring(0, 50);
                            }
                            netBookMark.setNetName(linkText);
                            netBookMark.setTypeId(bookMarkTypeId);
                            baseMapper.insert(netBookMark);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeAllBookMark() {
        QueryWrapper<NetBookmark> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("user_id");
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void saveBookMark(NetBookmark bookMark) {
        String typeId = bookMark.getTypeId();
        baseMapper.insert(bookMark);
        NetBookMarkType bookMarkType = bookMarkTypeService.getById(typeId);
        // 添加书签时将该书签类型的书签数量加一
        bookMarkType.setAmount(bookMarkType.getAmount() + 1);
        bookMarkTypeService.updateById(bookMarkType);
    }

    @Override
    public void removeBookMarkById(String id) {
        QueryWrapper<NetBookmark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        NetBookmark netBookMark = baseMapper.selectOne(queryWrapper);
        NetBookMarkType bookMarkType = bookMarkTypeService.getById(netBookMark.getTypeId());
        // 删除书签时将该书签类型的书签数量减一
        bookMarkType.setAmount(bookMarkType.getAmount() - 1);
        bookMarkTypeService.updateById(bookMarkType);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public R saveBookmark(NetBookmark bookMark) {

        return null;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

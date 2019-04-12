package com.mybatistest.test;

import com.mybatistest.pojo.Category;
import com.mybatistest.pojo.Product;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.peer.ListPeer;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testpart {
    SqlSession session;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void after(){
        session.commit();
        session.close();
    }

    @Test
    public void test() throws IOException {


        //查Category c = session.selectOne("getCategory",2);
        //增session.insert("addCategory",c);
        //删session.delete("deleteCategory",c);

//        改
//        Category c = session.selectOne("getCategory",2);
//        c.setName("修改了的Category名称");
//        session.update("updateCategory",c);

        listAll(session);

        session.commit();
        session.close();
    }

    @Test
    public void test1() throws IOException{
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("id",2);
//        params.put("name","cat");
//
//        List<Category> cs = session.selectList("listCategoryByIdAndName",params);
//        for(Category c:cs)
//            System.out.println(c.getName());
//
//        session.commit();
//        session.close();
    }

    @Test
    public void test2() throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
//
//        List<Product> ps = session.selectList("listProduct");
//        for(Product p:ps){
//            System.out.println(p+" 对应分类是:\t" +p.getCategory());
//        }
//        session.commit();
//        session.close();
    }

    @Test
    public void test3(){
        List<Category> cs = session.selectList("listCategory");
        for(Category c:cs){
            System.out.println(c);
            List<Product> ps = c.getProducts();
            for(Product p:ps)
                System.out.println(p);
        }
    }

    @Test
    public void test4(){  //mybatis if
        System.out.println("查询所有的");
        List<Product> ps = session.selectList("getProduct");
        for(Product p:ps)
            System.out.println(p);

        System.out.println("模糊查询");
        Map<String,Object> params = new HashMap<>();
        params.put("name","ip");
        List<Product> ps2 = session.selectList("getProduct",params);
        for (Product p:ps2)
            System.out.println(p);
    }

    private static void listAll(SqlSession session){
        List<Category> cs = session.selectList("listCategory");
        for(Category c:cs)
            System.out.println(c.getName());
    }
}

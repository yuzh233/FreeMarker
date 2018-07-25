<html>
<head>
    <meta charset="UTF-8">
    <title>QuickStart</title>
</head>
<body>
<#--访问Map中的Key-->
<h2>hello,${key}</h2>

<#--访问Pojo中的属性-->
<h2>POJO:${pojo}</h2>
<#-- 如果数值超过一千，会出现千分位，使用 ?c 去除-->
<h2>POJO:${pojo.id?c}</h2>
<h2>POJO:${pojo.name}</h2>

<#-- 取集合中的值 -->
<#list personList as person>
<#-- 下标支持运算 -->
下标：${person_index+1}，当前元素：${person}
</#list>

<#-- 遍历取Map数据，item是key，personMap是map名字 -->
<#list personMap?keys as item>
    ${item_index} -- ${personMap[item]}
</#list>
<#-- 根据key取Map值 -->
${personMap.key1}
${personMap.key2}
${personMap.key3}
${personMap.key4}
${personMap.key5}

<#-- 判断 -->
<#list personMap?keys as item>
    <#if item_index == 0>
        ${personMap[item].name}
    </#if>
    <#if item_index %2 == 0>
        这是偶数项 - ${item_index}
        <#-- else是单标签，在if里面使用 -->
        <#else >
        这是奇数项 - ${item_index}
    </#if>
</#list>奇数

<#-- 日期类型格式化 -->
当前日期：${date?date}
当前时间：${date?time}
当前日期时间：${date?datetime}
自定义日期格式:${date?string("yyyy/MM/dd HH:mm:ss")}

<#-- Null值的处理 -->
${test!"没有这个key就设置默认的值"}

<#if testnull??>
    not null..
    <#else >
    null..
</#if>

<#if testnull?exists>
    not null..
<#else >
    null..
</#if>

<#-- include -->
<#include "child.ftl">

</body>
</html>
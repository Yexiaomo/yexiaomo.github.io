# [我的个人网站代码](http://www.fanxiaobo.cn)
### 增加自定义404页面(仅错误的静态html页面)
- 写好404页面并上传至Nginx指定网站的根目录下(我的是/var/www/html/)
- 打开nginx的配置文件(/etc/nginx/nginx.conf),在http定义域内添加代码 `fastcgi_intercept_errors on;`
- 打开nginx的站点配置文件(/etc/nginx/sites-avaliable/ 下的default文件),在server定义域内添加如下代码
```text
#注释都可以删去,如果不知道添加到哪里就在location / {} 的后面添加
# 定义错误页面码，如果出现相应的错误页面码，转发到那里。
error_page 404 403 500 502 503 504 /404.html;
# 承接上面的location。
location = /404.html {
# 放错误页面的目录路径。
root  /var/www/html;
}
```
- 最后重启nginx服务`sudo service nginx reload` 

2018/4/24 21:05

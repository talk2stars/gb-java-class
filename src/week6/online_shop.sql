#用户表
DROP TABLE IF EXISTS 'user'
CREATE TABLE 'user'
(
	'id'	      bigint       not null auto_increment  	        comment 'id'
	'user_id'     bigint	   not null default -1                  comment '用户id' 
	'name'	      varchar(255) not null default ''	                comment '名字'
	'account'     varchar(255) not null default ''	                comment '账号'
	'password'    varchar(255) not null default ''                  comment '密码hash'
	'address'     varchar(255) not null default ''                  comment '地址'
	'telephone'   varchar(20)  not null default ''                  comment '电话'
	'create_time' datetime 	   not null default current_timestamp() comment '创建时间'
	'update_time' datetime              default current_timestamp() comment '修改时间'
	primary key ('id')
	key 'user_id' ('user_id')
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4


#商品表
DROP TABLE IF EXISTS 'commodity'
CREATE TABLE 'commodity'
(
        'id'          bigint       not null                             comment 'id'
        'name'        varchar(255) not null default ''                  comment '商品名称'
        'price'       int          not null default 0                   comment '价格'
        'create_time' datetime     not null default current_timestamp() comment '创建时间'
        'update_time' datetime              default current_timestamp() comment '修改时间'
	primary key ('id')
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
                
#订单表
DROP TABLE IF EXISTS 'order'
CREATE TABLE 'order'
(
        'order_id'     varchar(255) not null                             comment '订单id'
        'user_id'      bigint       not null default -1                  comment '用户id'
        'name'         varchar(255) not null default ''                  comment '订单名称'
        'snapshot'     varchar(255) not null default ''                  comment '订单快照'
        'pay_time'     datetime     not null default current_timestamp() comment '付款时间'
        'deliver_time' datetime              default current_timestamp() comment '发货时间'
	'receive_time' datetime              default current_timestamp() comment '收货时间'
        'create_time'  datetime              default current_timestamp() comment '创建时间'
        'update_time'  datetime              default current_timestamp() comment '修改时间'
	primary key ('order_id')
	key 'user_id ('user_id')      
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4		

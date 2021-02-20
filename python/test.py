import string

def hihi(*h):
    print(h)
    for i in h:
        print(i)


class test:
    def __init__(self):
        self.a = 1
        self.b = 2 

if __name__ == "__main__":
    # a = "id__di"
    # b = a.split("__")
    # test = test()
    # c = getattr(test,'a')
    # b.append((c<1))
    # print(b)
    print(string.ascii_letters+string.digits)


# create database fastapi;

# use fastapi;

# create table users
# (
#     id              int auto_increment
#         primary key,
#     status          enum ('active', 'deleted', 'blocked') default 'active'          not null,
#     email           varchar(255)                                                    null,
#     pw              varchar(2000)                                                   null,
#     name            varchar(255)                                                    null,
#     phone_number    varchar(20)                                                     null,
#     profile_img     varchar(1000)                                                   null,
#     sns_type        enum ('FB', 'G', 'K', 'Email')                                  null,
#     marketing_agree tinyint(1)                            default 0                 null,
#     updated_at      datetime                              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
#     created_at      datetime                              default CURRENT_TIMESTAMP not null
# );

# create table api_keys
# (
#     id              int auto_increment 
#     	primary key,
#     access_key varchar(64) not null,
#     secret_key varchar(64) not null,
#     user_memo varchar(40) null,
#     status enum('active','stopped','deleted') default 'active',
#     is_whitelisted bool default false,
#     user_id int not null,
#     updated_at      datetime                              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
#     created_at      datetime                              default CURRENT_TIMESTAMP not null,
#     foreign key(user_id) references users(id)
# );

# create table api_whitelists
# (
# 	id              int auto_increment
#         primary key,
#     ip_addr varchar(64) not null,
#     api_key_id int not null,
# 	updated_at      datetime                              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
#     created_at      datetime                              default CURRENT_TIMESTAMP not null,
# 	foreign key(api_key_id) references api_keys(id)
# );


# select * from users;
# select * from api_keys;

# drop table api_keys;
# drop table api_whitelists;





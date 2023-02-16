import grequests
import json
import requests

# 获取token
ip = "10.67.16.159"
oauth_consumer_key = "TrueLink"
oauth_consumer_secret = '12345678'
username = "administrator"
password = "888888"

headers = {
    'Accept': 'application/json',
    "Content-Type": "application/x-www-form-urlencoded",
    "API-Level": '3'
}
data_key = {
    "oauth_consumer_key": oauth_consumer_key,
    "oauth_consumer_secret": oauth_consumer_secret,
}
# 获取account_token
rep_token = requests.post('http://%s/api/v1/system/token' % ip, data=data_key, headers=headers)
account_token = json.loads(rep_token.content.decode('utf-8'))["account_token"]
# print("获取token结果:\n" + rep_token.content.decode('utf-8'))

# 登录MCU
data_auth = {
    "username": username,
    "password": password,
    "account_token": account_token
}
rep_login = requests.post('http://%s/api/v1/system/login' % ip, data=data_auth, headers=headers)
# print(rep_login.json())
cookie = {}
cookie["SSO_COOKIE_KEY"] = rep_login.headers['Set-Cookie'].split(";")[0].split("=")[1]

params = {
    "account_token": account_token,
}


# 获取所有会议列表：
def get_conflist():
    get_confs = requests.get('http://%s/api/v1/vc/confs?count=0' % ip, params=params, headers=headers, cookies=cookie)
    get_confs = json.loads(get_confs.content.decode('utf-8'))
    return get_confs


# 创建会议
def creat_conf(id):
    params = {"create_type": 3, "template_id": id}
    data_createconf = {}
    data_createconf["params"] = json.dumps(params)
    data_createconf["account_token"] = account_token
    create_conf = requests.post('http://%s/api/v1/mc/confs' % ip, data=data_createconf, headers=headers,
                                cookies=cookie)
    print("创建会议结果：\n" + create_conf.content.decode('utf-8'))


# 结束所有会议
def del_allconf():
    conflist = get_conflist()
    conf_sum = conflist["total"]
    for i in range(conf_sum):
        conf_e164 = conflist["confs"][i]["conf_id"]
        try:
            rep_delconf = requests.delete(f'http://{ip}/api/v1/mc/confs/{conf_e164}', params=params, headers=headers,
                                          cookies=cookie)
            print(rep_delconf.json())
        except Exception as e:
            print(f"结束会议{conf_e164}失败,原因是{e}")


# 获取个人模板列表
def get_personal_templates():
    get_confs = requests.get('http://%s/api/v1/mc/personal_templates?count=0' % ip, params=params, headers=headers,
                             cookies=cookie)
    personal_templates = json.loads(get_confs.content.decode('utf-8'))
    return personal_templates


# 模板并发创建会议
def err_handler(request, exception):
    print("请求出错")


def creat_conf_any(template_lists):
    print("将要执行的模板数量：%s 列表：%s" % (len(template_lists), template_lists))
    sum = input("输入并发数量：")
    creat_conf_list = []
    for i in template_lists:
        params = {"create_type": 3, "template_id": i}
        data_createconf = {}
        data_createconf["params"] = json.dumps(params)
        data_createconf["account_token"] = account_token
        creat_conf_list.append(
            grequests.post('http://%s/api/v1/mc/confs' % ip, data=data_createconf, headers=headers, cookies=cookie))
    res_list = grequests.map(creat_conf_list, size=int(sum), exception_handler=err_handler)
    for response in res_list:
        print(response.content.decode('utf-8'))


def add_mt():
    creat_conf_list = []
    params = {
        "from_audiences": 0,
        "mts": [
            {
                "account": "2322231",
                "account_type": 5,
                "bitrate": 2048,
                "protocol": 0,
                "forced_call": 0,
                "call_mode": 0
            },
            {
                "account": "2322232",
                "account_type": 5,
                "bitrate": 2048,
                "protocol": 0,
                "forced_call": 0,
                "call_mode": 0
            }
        ]
    }

    data_createconf = {}
    data_createconf["params"] = json.dumps(params)
    data_createconf["account_token"] = account_token
    creat_conf_list.append(
        grequests.post(f'http://%s/api/v1/vc/confs/8888717/mts' % ip, data=data_createconf, headers=headers,
                       cookies=cookie))
    res_list = grequests.map(creat_conf_list, size=int(1), exception_handler=err_handler)
    for response in res_list:
        print(response.content.decode('utf-8'))
        print(response.content.decode('utf-8'))


def choice_template():
    template_b = {}
    for i in range(0, get_personal_templates()['total']):
        tem_name = get_personal_templates()['personal_templates'][i]['name']
        tem_id = get_personal_templates()['personal_templates'][i]['template_id']
        template_a = (tem_name, tem_id)
        template_b[i] = template_a

    # num = input("输入你的选择")
    for key in template_b.keys():
        print(key, template_b[key][0])
    num = input("选择你的模板,多个模板以英文','隔开:")
    if num == 'b':
        print("返回上一层")
        return
    choice_list = num.split(',')
    template_lists = []
    for i in choice_list:
        i = int(i)
        if i in template_b.keys():
            tem_id = template_b[i][1]
            template_lists.append(tem_id)
        else:
            print("模板列表中不存在%s" % i)
    return template_lists


def creat_conf_alltem(sum):
    tem_list = []
    for i in range(0, len(get_personal_templates()['personal_templates'])):
        tem_list.append(get_personal_templates()['personal_templates'][i]['template_id'])
    print("将要执行的模板数量：%s 列表：%s" % (len(tem_list), tem_list))
    creat_conf_list = []
    for i in tem_list:
        params = {"create_type": 3, "template_id": i}
        data_createconf = {}
        data_createconf["params"] = json.dumps(params)
        data_createconf["account_token"] = account_token
        creat_conf_list.append(
            grequests.post('http://%s/api/v1/mc/confs' % ip, data=data_createconf, headers=headers, cookies=cookie))
    res_list = grequests.map(creat_conf_list, size=int(sum), exception_handler=err_handler)
    for response in res_list:
        print(response.content.decode('utf-8'))


if __name__ == '__main__':
    add_mt()

    menu = """
    0、退出菜单
    1、获取所有会议
    2、结束所有会议
    3、使用所有模板召开会议
    4、使用选择模板召开会议
    """
    while True:
        ch = input("%s请输入你的选择:" % menu)
        if ch == '1':
            conf_list = get_conflist()
            print(conf_list)
        elif ch == '2':
            del_allconf()
        elif ch == '3':
            sum = input("输入并发数量：")
            sum = int(sum)
            creat_conf_alltem(sum)
        elif ch == '4':
            creat_conf_any(choice_template())
        elif ch == '0':
            break

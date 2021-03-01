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






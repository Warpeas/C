import hashlib
def remove_same_piture_by_get_md5(path):
    img_list = os.listdir(path)
    print(img_list)
    md5_list =[]
    for filename in img_list:
        m = hashlib.md5()
        mfile = open(os.path.join(path,filename), "rb")
        m.update(mfile.read())
        mfile.close()
        md5_value = m.hexdigest()
        #print(md5_value)
        if (md5_value in md5_list):
            os.remove(os.path.join(path,filename))
        else:
            md5_list.append(md5_value)
            print('total %s images'%len(md5_list))
import md5
import os
from time import clock as now


def getmd5(filename):
    file_txt = open(filename, 'rb').read()
    m = md5.new(file_txt)
    return m.hexdigest()


def main():
    allfiles = "F:\RenYongguo\\cats_remove"
    # all_md5 = []
    # total_file = 0
    # total_delete = 0
    start = now()
    for dir in os.listdir(allfiles):
        all_md5 = []
        total_file = 0
        total_delete = 0
        path = os.path.join(allfiles, dir)
        print(path)
        for file in os.listdir(path):
            total_file += 1
            real_path = os.path.join(path, file)
            if os.path.isfile(real_path) == True:
                filemd5 = getmd5(real_path)
                if filemd5 in all_md5:
                    total_delete += 1
                    os.remove(real_path)
                    # print u'删除', file
                else:
                    all_md5.append(filemd5)
        end = now()
        time_last = end - start
        print ('文件总数：', total_file)
        print ('删除个数：', total_delete)
    print ('耗时：', time_last, '秒')


if __name__ == '__main__':
    main()

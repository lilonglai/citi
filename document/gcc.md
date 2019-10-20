https://mirrors.ustc.edu.cn/gnu/gcc/gcc-8.3.0/gcc-8.3.0.tar.gz

https://www.mpfr.org/mpfr-current/mpfr-4.0.2.tar.gz
https://mirrors.ustc.edu.cn/gnu/mpfr/mpfr-3.1.4.tar.gz

https://ftp.gnu.org/gnu/mpc/mpc-1.0.2.tar.gz


https://gmplib.org/download/gmp/gmp-6.1.2.tar.xz

./configure --prefix=/home/kevin/app/gmp6.1.2
make
make install

./configure --prefix=/home/kevin/app/mpfr4.0.2 --with-gmp=/home/kevin/app/gmp6.1.2
make
make install

./configure --prefix=/home/kevin/app/mpfr3.1.4 --with-gmp=/home/kevin/app/gmp6.1.2
make
make install




./configure --prefix=/home/kevin/app/mpc1.0.2 --with-gmp=/home/kevin/app/gmp6.1.2 --with-mpfr=/home/kevin/app/mpfr3.1.4

将mul.c内的所有mpfr_fmma函数改名为mpfr_fmma_mul, 3处
make
make install

./configure --prefix=/home/kevin/app/isl0.18 --with-gmp-prefix=/home/kevin/app/gmp6.1.2
make -j 4
make install

export LD_LIBRARY_PATH=/home/kevin/app/gmp6.1.2/lib:/home/kevin/app/mpfr3.1.4/lib:/home/kevin/app/mpc1.0.2/lib:/home/kevin/app/isl0.18/lib

tar xzvf gcc-8.3.0.tar.gz

cd gcc-8.3.0

mkdir bld

cd bld


../configure --prefix=/home/kevin/app/gcc8.3 --enable-threads=posix --disable-multilib --with-cpu=generic --enable--long-long --enable-languages=c,c++  --with-gmp=/home/kevin/app/gmp6.1.2 --with-mpfr=/home/kevin/app/mpfr3.1.4 --with-mpc=/home/kevin/app/mpc1.0.2 --with-isl=/home/kevin/app/isl0.18 --build=x86_64-redhat-linux



make
make install
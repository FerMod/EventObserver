#!/bin/sh
set -e # Exit with nonzero exit code if anything fails

echo "Cloning doxygen repository..."
git clone https://github.com/doxygen/doxygen.git doxygen
cd doxygen

echo "Create build folder..."
mkdir build
cd build

echo "Build doxygen..."
cmake -G "Unix Makefiles" ..
make

echo "Installing doxygen..."
cp ./bin/* $TRAVIS_BUILD_DIR

cd $TRAVIS_BUILD_DIR
echo "Finished installing doxygen"

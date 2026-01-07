#!/bin/Sh

###
# #%L
# kftc-financial-institution-info
# %%
# Copyright (C) 2024 - 2025 Jinahya, Inc.
# %%
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#      http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# #L%
###
sha1sum bankinfo.hwp > _sha1sum.txt
sha1sum bankinfo.hwp.pdf >> _sha1sum.txt
sha1sum bankinfo.pdf >> _sha1sum.txt
sha1sum codechgfile.text >> _sha1sum.txt
sha1sum codechgfile.text.xlsx >> _sha1sum.txt
sha1sum codechgfile.xls >> _sha1sum.txt
sha1sum codefilex.text >> _sha1sum.txt
sha1sum codefilex.text.xlsx >> _sha1sum.txt
sha1sum codefilex.xls >> _sha1sum.txt

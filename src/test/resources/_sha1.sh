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
sha1sum bankinfo.hwp > bankinfo.hwp.sha1
sha1sum bankinfo.hwp.pdf > bankinfo.hwp.pdf.sha1
sha1sum bankinfo.pdf > bankinfo.pdf.sha1
sha1sum codechgfile.text > codechgfile.text.sha1
sha1sum codechgfile.text.xlsx > codechgfile.text.xlsx.sha1
sha1sum codechgfile.xls > codechgfile.xls.sha1
sha1sum codefilex.text > codefilex.text.sha1
sha1sum codefilex.text.xlsx > codefilex.text.xlsx.sha1
sha1sum codefilex.xls > codefilex.xls.sha1

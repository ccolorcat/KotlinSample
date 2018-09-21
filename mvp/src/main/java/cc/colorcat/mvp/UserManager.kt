/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.mvp

import cc.colorcat.mvp.entity.User

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
object UserManager : ObjectOwner<User> {
    private var mUser = User("001", "ccolorcat")

    override val mObservers: MutableSet<ObjectObserver<User>> = LinkedHashSet()

    override fun get(): User {
        return mUser
    }

    override fun update(data: User) {
        if (mUser != data) {
            forceUpdateUser(data)
        }
    }

    private fun forceUpdateUser(user: User) {
        mUser = user
        mObservers.forEach { it.onReceive(mUser) }
    }
}
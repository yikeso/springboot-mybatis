package com.china.ciic.bookgenerate.handle.encode;


import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;

public abstract class EncodeComponent {

	public abstract ChapterEntity operation() throws Exception;
}

package ai.energyx.bems.upload.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ai.energyx.bems.upload.domain.UploadFile;

@Component
public class FileStore {
	@Value("${file.dir}")
	private String fileDir;

	public String getFullPath(String filename) {
		return fileDir + filename;
	}

	public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) {
		return multipartFiles.stream().map(multipartFile -> {
			try {
				return storeFile(multipartFile);
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}).toList();
	}

	public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}
		String originalFilename = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFilename);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		return new UploadFile(originalFilename, storeFileName);
	}

	private String createStoreFileName(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(pos + 1);

		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}